import { IEcommerce } from '@/shared/model/ecommerce.model';

export interface IEcommerceProcess {
  id?: number;
  processInstance?: any | null;
  ecommerce?: IEcommerce | null;
}

export class EcommerceProcess implements IEcommerceProcess {
  constructor(public id?: number, public processInstance?: any | null, public ecommerce?: IEcommerce | null) {}
}
