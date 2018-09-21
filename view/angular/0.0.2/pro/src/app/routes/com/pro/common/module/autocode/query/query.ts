export class Query {
  queryId: string;
  queryType: string;
  dtId: string;
  columnsId: string;
  weightOrder: number;

  constructor() {
    this.queryId = '';
    this.queryType = '';
    this.dtId = '';
    this.columnsId = '';
    this.weightOrder = 0;
  }
}
