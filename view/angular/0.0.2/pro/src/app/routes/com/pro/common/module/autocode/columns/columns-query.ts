export class ColumnsQuery {
  columnsId: string;
  columnName: string;
  columnNameAnnotation: string;
  dataType: string;
  isNull: string;
  initialCaseColumnName: string;
  initialLowercaseColumnName: string;
  weightOrder: number;
  dtId: string;
  uniqueColumnName: string;
  uniqueDtId: string;
  inColumnsId: Array<string>;
  inDtId: Array<string>;

  constructor() {
    this.columnsId = '';
    this.columnName = '';
    this.columnNameAnnotation = '';
    this.dataType = '';
    this.isNull = '';
    this.initialCaseColumnName = '';
    this.initialLowercaseColumnName = '';
    this.weightOrder = 0;
    this.dtId = '';
    this.uniqueColumnName = '';
    this.uniqueDtId = '';
    this.inColumnsId = new Array();
    this.inDtId = new Array();
  }
}
