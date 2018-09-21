export class DtQuery {
  dtId: string;
  dtSql: string;
  dtName: string;
  dtNameAnnotation: string;
  dtPrefix: string;
  initialCaseEntityName: string;
  initialLowercaseEntityName: string;
  proPath: string;
  proAllName: string;
  proName: string;
  uniqueDtName: string;
  inDtId: Array<string>;

  constructor() {
    this.dtId = '';
    this.dtSql = '';
    this.dtName = '';
    this.dtNameAnnotation = '';
    this.dtPrefix = '';
    this.initialCaseEntityName = '';
    this.initialLowercaseEntityName = '';
    this.proPath = '';
    this.proAllName = '';
    this.proName = '';
    this.uniqueDtName = '';
    this.inDtId = new Array();
  }
}
