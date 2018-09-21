export class DenglurizhiQuery {
  denglurizhiId: string;
  zhanghaoId: string;
  account: string;
  nicheng: string;
  denglushijian: number;
  dengluxinxi: string;
  inDenglurizhiId: Array<string>;

  constructor() {
    this.denglurizhiId = '';
    this.zhanghaoId = '';
    this.account = '';
    this.nicheng = '';
    this.denglushijian = 0;
    this.dengluxinxi = '';
    this.inDenglurizhiId = new Array();
  }
}
