export class SortQuery {
  sortId: string;
  sortRule: string;
  dtId: string;
  columnsId: string;
  uniqueDtId: string;
  uniqueColumnsId: string;
  inSortId: Array<string>;

  constructor() {
    this.sortId = '';
    this.sortRule = '';
    this.dtId = '';
    this.columnsId = '';
    this.uniqueDtId = '';
    this.uniqueColumnsId = '';
    this.inSortId = new Array();
  }
}
