import {ModuleWithProviders, NgModule} from '@angular/core';
import {NotificationLoaderComponent} from "./components/notification.loader.component";
import {CommonModule} from "@angular/common";
import {CommonService} from "./services/common.service";
import {HttpService} from "./services/http.service";
import {AmexioWidgetModule} from "amexio-ng-extensions";
import {SharedService} from "./services/shared.service";
import {CookieService} from "ngx-cookie-service";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {AuthCanLoadService} from "./services/auth.canload.service";
import {AuthCanActiveService} from "./services/auth.canActive.service";
import {CatlogResolverService} from "./services/catlog.resolver.service";

@NgModule({
  declarations: [NotificationLoaderComponent],
  imports: [CommonModule,
            HttpClientModule,
            FormsModule,
            ReactiveFormsModule,
            AmexioWidgetModule],
  exports: [NotificationLoaderComponent],
  providers: [SharedService,CommonService, HttpService,
    CookieService, AuthCanLoadService,
    CatlogResolverService,
    AuthCanActiveService]
})
export class SharedlibModule {

  static forRoot(): ModuleWithProviders {
    return {
      ngModule: SharedlibModule,
      providers: [SharedService, AuthCanLoadService,
        AuthCanActiveService, CatlogResolverService]
    };
  }
}
