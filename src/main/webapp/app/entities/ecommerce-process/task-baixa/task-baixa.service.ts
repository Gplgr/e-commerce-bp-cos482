import axios from 'axios';
import { TaskBaixaContext } from './task-baixa.model';

const baseApiUrl = 'api/ecommerce-process/task-baixa';

export default class TaskBaixaService {
  public loadContext(taskId: number): Promise<TaskBaixaContext> {
    return new Promise<TaskBaixaContext>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${taskId}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public claim(taskId: number): Promise<TaskBaixaContext> {
    return new Promise<TaskBaixaContext>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${taskId}/claim`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public complete(taskBaixaContext: TaskBaixaContext): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/complete`, taskBaixaContext)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
