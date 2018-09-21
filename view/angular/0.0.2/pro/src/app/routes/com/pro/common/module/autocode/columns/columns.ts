export class Columns {
  columnsId: string;
  columnName: string;
  columnNameAnnotation: string;
  dataType: string;
  isNull: string;
  initialCaseColumnName: string;
  initialLowercaseColumnName: string;
  weightOrder: number;
  dtId: string;

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
  }
}
