import axios from 'axios';

import { IEcommerceProcess } from '@/shared/model/ecommerce-process.model';

const baseApiUrl = 'api/ecommerce-processes';

export default class EcommerceProcessService {
  public find(id: number): Promise<IEcommerceProcess> {
    return new Promise<IEcommerceProcess>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${id}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public retrieve(): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .get(baseApiUrl)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public create(entity: IEcommerceProcess): Promise<IEcommerceProcess> {
    return new Promise<IEcommerceProcess>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
