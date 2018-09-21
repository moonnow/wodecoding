export class GroupQuery {
  groupId: string;
  name: string;
  uniqueName: string;
  inGroupId: Array<string>;

  constructor() {
    this.groupId = '';
    this.name = '';
    this.uniqueName = '';
    this.inGroupId = new Array();
  }
}
