export class InfoQuery {
  infoId: string;
  cellphone: string;
  email: string;
  userId: string;
  uniqueCellphone: string;
  eachUniqueEmail: string;
  uniqueEmail: string;
  inInfoId: Array<string>;

  constructor() {
    this.infoId = '';
    this.cellphone = '';
    this.email = '';
    this.userId = '';
    this.uniqueCellphone = '';
    this.eachUniqueEmail = '';
    this.uniqueEmail = '';
    this.inInfoId = new Array();
  }
}
