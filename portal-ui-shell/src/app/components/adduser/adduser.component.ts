import { Component, OnInit } from '@angular/core';
import {AddUserModel} from '../../models/adduser.model';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import {HttpService} from '../../services/http.service';
import {SERVICE_URL} from '../../constant/service.constant';
import {CommonService} from '../../services/common.service';
@Component({
  selector: 'add-user',
  templateUrl: './adduser.component.html',
  styleUrls: ['./adduser.component.scss']
})
export class AddUserComponent implements OnInit {
addUserForm: any;
addUserModel: AddUserModel;
  constructor(private fb: FormBuilder,
              private _cService: CommonService,
              private _httpService: HttpService,
              private _route: Router) {
    this.addUserModel = new AddUserModel();
   }

  ngOnInit() {
    this.validateFieldDefinationFields();
  }
  validateFieldDefinationFields() {
    this.addUserForm = this.fb.group({
      userId: ['userId', [Validators.required]],
      password: ['password', [Validators.required]],
      firstName: ['firstName', [Validators.required]],
      lastName: ['lastName', [Validators.required]],
      emailId : ['emailId', [Validators.required]],
      PhoneNo : ['PhoneNo', [Validators.required]]
    });
  }
  onBackClick() {
    this._route.navigate(['login']);
  }

  addUser() {
    try {
      this._httpService.restCall(SERVICE_URL.ADD_USER, 'post', this.addUserModel).toPromise()
        .then((res: any) => {
          this._cService.showLoader = false;
            this._route.navigate(['login']);
        });
    } catch (error) {
    }
  }
}
