/**
 * Created by dattaram on 14/8/19.
 */
import {Component, OnInit} from '@angular/core';
import {CommonService} from "../services/common.service";

@Component({
  selector: 'notification-loader',
  templateUrl: 'notification.loader.component.html'
})

export class NotificationLoaderComponent implements OnInit {
  constructor(public _cService: CommonService) {
  }

  ngOnInit() {
  }
}
