import { Component, OnInit } from '@angular/core';
import { Geolocation } from '@ionic-native/geolocation/ngx';

interface Position {
  latitude: number;
  longitude: number;
}

@Component({
  selector: 'app-tab3',
  templateUrl: 'tab3.page.html',
  styleUrls: ['tab3.page.scss']
})
export class Tab3Page implements OnInit {

  public currentPosition: Position = {
    latitude: 0,
    longitude: 0
  };

  constructor(private geolocation: Geolocation) { }

  ngOnInit(): void {
    this.geolocation.getCurrentPosition().then((resp) => {
      this.currentPosition = {
        latitude: resp.coords.latitude,
        longitude: resp.coords.longitude
      };
    }).catch((error) => {
      console.log('Error getting location', error);
    });
  }

}
