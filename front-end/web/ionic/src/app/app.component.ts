import { Component } from '@angular/core';

import { TranslateService } from '@ngx-translate/core';
import { Menu } from './app.module';

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html'
})
export class AppComponent {

  public appMenu: Menu[];

  constructor(
    private translate: TranslateService
  ) {
    this.initializeApp();
  }

  initializeApp() {
    this.initializeLanguage();
    this.initializeMenu();
  }

  initializeLanguage() {
    // Sets the default language to use as a fallback
    this.translate.setDefaultLang('es');
    // Changes the lang currently used
    this.translate.use('es');
  }

  initializeMenu() {

    this.appMenu = [
      { title: '', url: '/home', icon: 'home' },
      { title: '', url: '/list', icon: 'list' },
      { title: '', url: '/geolocation', icon: 'locate' },
      { title: '', url: '/about-us', icon: 'contact' },
    ];

    this.translate.get('MENU.OPT1.LABEL').subscribe(
      (res: string) => {
        this.appMenu[0].title = res;
      }
    );

    this.translate.get('MENU.OPT2.LABEL').subscribe(
      (res: string) => {
        this.appMenu[1].title = res;
      }
    );

    this.translate.get('MENU.OPT3.LABEL').subscribe(
      (res: string) => {
        this.appMenu[2].title = res;
      }
    );

    this.translate.get('MENU.OPT4.LABEL').subscribe(
      (res: string) => {
        this.appMenu[3].title = res;
      }
    );

  }

}
