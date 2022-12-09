export interface IProduto {
  id?: number;
  name?: string | null;
  price?: number | null;
}

export class Produto implements IProduto {
  constructor(public id?: number, public name?: string | null, public price?: number | null) {}
}
