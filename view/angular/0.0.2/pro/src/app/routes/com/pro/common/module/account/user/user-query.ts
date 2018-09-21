export class UserQuery {
  userId: string;
  name: string;
  idNumber: string;
  uniqueIdNumber: string;
  inUserId: Array<string>;

  constructor() {
    this.userId = '';
    this.name = '';
    this.idNumber = '';
    this.uniqueIdNumber = '';
    this.inUserId = new Array();
  }
}
