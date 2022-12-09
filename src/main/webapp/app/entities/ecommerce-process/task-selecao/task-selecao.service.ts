import axios from 'axios';
import { TaskSelecaoContext } from './task-selecao.model';

const baseApiUrl = 'api/ecommerce-process/task-selecao';

export default class TaskSelecaoService {
  public loadContext(taskId: number): Promise<TaskSelecaoContext> {
    return new Promise<TaskSelecaoContext>((resolve, reject) => {
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

  public claim(taskId: number): Promise<TaskSelecaoContext> {
    return new Promise<TaskSelecaoContext>((resolve, reject) => {
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

  public complete(taskSelecaoContext: TaskSelecaoContext): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/complete`, taskSelecaoContext)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
