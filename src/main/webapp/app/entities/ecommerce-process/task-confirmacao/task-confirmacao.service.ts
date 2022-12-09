import axios from 'axios';
import { TaskConfirmacaoContext } from './task-confirmacao.model';

const baseApiUrl = 'api/ecommerce-process/task-confirmacao';

export default class TaskConfirmacaoService {
  public loadContext(taskId: number): Promise<TaskConfirmacaoContext> {
    return new Promise<TaskConfirmacaoContext>((resolve, reject) => {
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

  public claim(taskId: number): Promise<TaskConfirmacaoContext> {
    return new Promise<TaskConfirmacaoContext>((resolve, reject) => {
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

  public complete(taskConfirmacaoContext: TaskConfirmacaoContext): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/complete`, taskConfirmacaoContext)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
