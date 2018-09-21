export class AccountQuery {
  accountId: string;
  account: string;
  password: string;
  userId: string;
  uniqueAccount: string;
  inAccountId: Array<string>;

  constructor() {
    this.accountId = '';
    this.account = '';
    this.password = '';
    this.userId = '';
    this.uniqueAccount = '';
    this.inAccountId = new Array();
  }
}
