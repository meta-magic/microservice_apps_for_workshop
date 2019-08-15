export class AddUserModel {
    userId: string;
    password: string;
    firstName : string;
    lastName: string;
    emailId:string;
    phoneNo: string;
    constructor() {
      this.password = '';
      this.userId = '';
      this.firstName = '';
      this.lastName = '';
      this.emailId = '';
      this.phoneNo = '';
    }
  }