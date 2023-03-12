import { Component, OnInit } from '@angular/core';
import {LocationService} from "./shared.locations/location.service";
import {Location} from "./shared.locations/location.model";

@Component({
  selector: 'app-locations',
  templateUrl: './locations.component.html',
  styleUrls: ['./locations.component.css']
})
export class LocationsComponent implements OnInit {

  locations: Location[];

  constructor(private locationService: LocationService) { }

  ngOnInit(): void {
    this.getLocations();
  }

  getLocations(): void{
    this.locationService.getLocations()
      .subscribe(locations => this.locations = locations);
  }

}
