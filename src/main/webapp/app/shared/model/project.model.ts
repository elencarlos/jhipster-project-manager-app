import { IArea } from 'app/shared/model/area.model';
import { ProjectStatus } from 'app/shared/model/enumerations/project-status.model';

export interface IProject {
  id?: number;
  title?: string | null;
  status?: ProjectStatus | null;
  areas?: IArea[] | null;
}

export const defaultValue: Readonly<IProject> = {};
