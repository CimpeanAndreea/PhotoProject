import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Picture} from "../../pictures/shared/picture.model";
import {Photoshoot} from "./photoshoot.model";
import {Client} from "../../clients/shared.clients/client.model";

@Injectable({
  providedIn: 'root'
})
export class PhotoshootService {
  private photoshootsUrl = 'http://localhost:8080/Gradle___ro_ubb_catalog___catalog_web_1_0_SNAPSHOT_war/api/photoshoots';

  constructor(private httpClient: HttpClient) { }

  getPhotoshoots(): Observable<Photoshoot[]>{
    return this.httpClient
      .get<Array<Photoshoot>>(this.photoshootsUrl);
  }

  getClientsForPhotoshoot(id: number): Observable<Client[]>{
    const url = `${this.photoshootsUrl}/${id}/clients`;
    return this.httpClient
      .get<Array<Client>>(url);
  }
}
