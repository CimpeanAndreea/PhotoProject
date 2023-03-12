import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {Photographer} from "./photographer.model";

@Injectable({
  providedIn: 'root'
})
export class PhotographerService {
  private photographersUrl = 'http://localhost:8080/Gradle___ro_ubb_catalog___catalog_web_1_0_SNAPSHOT_war/api/photographers';

  constructor(private httpClient: HttpClient) { }

  getPhotographers(): Observable<Photographer[]>{
    return this.httpClient
      .get<Array<Photographer>>(this.photographersUrl);
  }

  getPhotographer(id: number): Observable<Photographer>{
    return this.getPhotographers()
      .pipe(
        map(photographers => photographers.find(photographer => photographer.id === id))
      );
  }

  savePhotographer(photographer: Photographer):Observable<any>{
    return this.httpClient
      .post<Photographer>(this.photographersUrl, photographer);
  }

  updatePhotographer(photographer: Photographer): Observable<any>{
    const url = `${this.photographersUrl}/${photographer.id}`;
    return this.httpClient
      .put<Photographer>(url, photographer);
  }

  deletePhotographer(id: number): Observable<any>{
    const url = `${this.photographersUrl}/${id}`;
    return this.httpClient
      .delete(url);
  }

  //filterPhotographers()
  //sortPhotographers()
}
