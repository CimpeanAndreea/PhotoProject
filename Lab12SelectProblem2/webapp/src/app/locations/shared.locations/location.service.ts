import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {Location} from "./location.model";

@Injectable({
  providedIn: 'root'
})
export class LocationService {

  private locationsUrl = 'http://localhost:8080/Gradle___ro_ubb_catalog___catalog_web_1_0_SNAPSHOT_war/api/locations';

  constructor(private httpClient: HttpClient) { }

  getLocations(): Observable<Location[]>{
    return this.httpClient
      .get<Array<Location>>(this.locationsUrl);
  }
}
