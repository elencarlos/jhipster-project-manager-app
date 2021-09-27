import axios from 'axios';
import { createAsyncThunk, isFulfilled, isPending, isRejected } from '@reduxjs/toolkit';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { IQueryParams, createEntitySlice, EntityState, serializeAxiosError } from 'app/shared/reducers/reducer.utils';
import { IEfficiencyResult, defaultValue } from 'app/shared/model/efficiency-result.model';

const initialState: EntityState<IEfficiencyResult> = {
  loading: false,
  errorMessage: null,
  entities: [],
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

const apiUrl = 'api/efficiency-results';

// Actions

export const getEntities = createAsyncThunk('efficiencyResult/fetch_entity_list', async ({ page, size, sort }: IQueryParams) => {
  const requestUrl = `${apiUrl}?cacheBuster=${new Date().getTime()}`;
  return axios.get<IEfficiencyResult[]>(requestUrl);
});

export const getEntity = createAsyncThunk(
  'efficiencyResult/fetch_entity',
  async (id: string | number) => {
    const requestUrl = `${apiUrl}/${id}`;
    return axios.get<IEfficiencyResult>(requestUrl);
  },
  { serializeError: serializeAxiosError }
);

// slice

export const EfficiencyResultSlice = createEntitySlice({
  name: 'efficiencyResult',
  initialState,
  extraReducers(builder) {
    builder
      .addCase(getEntity.fulfilled, (state, action) => {
        state.loading = false;
        state.entity = action.payload.data;
      })
      .addMatcher(isFulfilled(getEntities), (state, action) => {
        return {
          ...state,
          loading: false,
          entities: action.payload.data,
        };
      })
      .addMatcher(isPending(getEntities, getEntity), state => {
        state.errorMessage = null;
        state.updateSuccess = false;
        state.loading = true;
      });
  },
});

export const { reset } = EfficiencyResultSlice.actions;

// Reducer
export default EfficiencyResultSlice.reducer;
