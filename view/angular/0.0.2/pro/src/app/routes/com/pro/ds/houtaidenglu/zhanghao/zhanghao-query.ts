export class ZhanghaoQuery {
  zhanghaoId: string;
  account: string;
  password: string;
  nicheng: string;
  uniqueAccount: string;
  inZhanghaoId: Array<string>;

  constructor() {
    this.zhanghaoId = '';
    this.account = '';
    this.password = '';
    this.nicheng = '';
    this.uniqueAccount = '';
    this.inZhanghaoId = new Array();
  }
}
