import {Injectable} from '@angular/core';

import {HttpClient} from "@angular/common/http";


import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {Client} from "./client.model";
import {Photoshoot} from "../../photoshoots/shared.photoshoots/photoshoot.model";

import { forkJoin } from 'rxjs';

@Injectable()
export class ClientService {
  private clientsUrl = 'http://localhost:8080/Gradle___ro_ubb_catalog___catalog_web_1_0_SNAPSHOT_war/api/clients';

  constructor(private httpClient: HttpClient) {
  }

  enroll(id: number, photoshoot: Photoshoot): Observable<any>{
    const url = `${this.clientsUrl}/${id}/photoshoots`
    return this.httpClient.post<Photoshoot>(url, photoshoot);

  }


  getClients(): Observable<Client[]> {
    return this.httpClient
      .get<Array<Client>>(this.clientsUrl);
  }

  filterClients(name: string): Observable<Client[]>{
    return this.httpClient
      .get<Array<Client>>(this.clientsUrl + '/filter/' + name);
  }

  getClient(id: number): Observable<Client> {
    return this.getClients()
      .pipe(
        map(clients => clients.find(client => client.id === id))
      );
  }

  getClientPhotoshoots(id: number): Observable<Photoshoot[]> {
    const url = `${this.clientsUrl}/${id}/${'photoshoots'}`;
    console.log(url);
    return this.httpClient
      .get<Array<Photoshoot>>(url);
  }

  saveClient(client: Client):Observable<any>{
    return this.httpClient
      .post<Client>(this.clientsUrl, client);
  }

  updateClient(client: Client): Observable<any> {
    const url = `${this.clientsUrl}/${client.id}`;
    return this.httpClient
      .put<Client>(url, client);
  }

  deleteClient(id: number): Observable<any>{
    const url = `${this.clientsUrl}/${id}`;
    return this.httpClient
      .delete(url);
  }



}
