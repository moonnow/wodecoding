export class Parameter {
  pro: number;
  action: string;
  primaryKey: string;
  rows: number;
  page: number;
  token: string;

  constructor() {
    this.pro = +new Date();
    this.action = '';
    this.primaryKey = '';
    this.rows = 10;
    this.page = 1;
    this.token = '';
  }
}
