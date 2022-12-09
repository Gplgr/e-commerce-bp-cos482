import axios from 'axios';

import { IEcommerce } from '@/shared/model/ecommerce.model';

const baseApiUrl = 'api/ecommerces';

export default class EcommerceService {
  public find(id: number): Promise<IEcommerce> {
    return new Promise<IEcommerce>((resolve, reject) => {
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
}
