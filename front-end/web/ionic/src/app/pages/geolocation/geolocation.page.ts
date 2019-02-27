import { Component, OnInit } from '@angular/core';
import { Geolocation } from '@ionic-native/geolocation/ngx';

interface Position {
  latitude: number;
  longitude: number;
}

@Component({
  selector: 'app-geolocation',
  templateUrl: './geolocation.page.html',
  styleUrls: ['./geolocation.page.scss'],
})
export class GeolocationPage implements OnInit {

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
