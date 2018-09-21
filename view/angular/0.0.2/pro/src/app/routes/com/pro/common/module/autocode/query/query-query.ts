export class QueryQuery {
  queryId: string;
  queryType: string;
  dtId: string;
  columnsId: string;
  weightOrder: number;
  uniqueDtId: string;
  uniqueColumnsId: string;
  inQueryId: Array<string>;

  constructor() {
    this.queryId = '';
    this.queryType = '';
    this.dtId = '';
    this.columnsId = '';
    this.weightOrder = 0;
    this.uniqueDtId = '';
    this.uniqueColumnsId = '';
    this.inQueryId = new Array();
  }
}
