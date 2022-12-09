import { IProduto } from '@/shared/model/produto.model';

export interface IEcommerce {
  id?: number;
  userID?: string | null;
  productQuantity?: number | null;
  confirmation?: boolean | null;
  invoiceCode?: number | null;
  invoicePrice?: number | null;
  saleDate?: Date | null;
  productsAvailable?: number | null;
  status?: boolean | null;
  produto?: IProduto | null;
}

export class Ecommerce implements IEcommerce {
  constructor(
    public id?: number,
    public userID?: string | null,
    public productQuantity?: number | null,
    public confirmation?: boolean | null,
    public invoiceCode?: number | null,
    public invoicePrice?: number | null,
    public saleDate?: Date | null,
    public productsAvailable?: number | null,
    public status?: boolean | null,
    public produto?: IProduto | null
  ) {
    this.confirmation = this.confirmation ?? false;
    this.status = this.status ?? false;
  }
}
