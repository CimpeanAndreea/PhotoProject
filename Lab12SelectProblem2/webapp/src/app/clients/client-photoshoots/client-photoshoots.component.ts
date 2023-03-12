import { Component, OnInit } from '@angular/core';
import {Photoshoot} from "../../photoshoots/shared.photoshoots/photoshoot.model";
import {ClientService} from "../shared.clients/client.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-client-photoshoots',
  templateUrl: './client-photoshoots.component.html',
  styleUrls: ['./client-photoshoots.component.css']
})
export class ClientPhotoshootsComponent implements OnInit {

  photoshoots: Photoshoot[];
  id: number;

  constructor(private route: ActivatedRoute, private clientService: ClientService) { }

  ngOnInit(): void {
    this.route.params.subscribe(
      params => {
        this.id = +params.id;
        console.log(this.id);
        this.getPhotoshoots()
      }
    );
  }

  getPhotoshoots(): void{
    this.clientService.getClientPhotoshoots(this.id)
      .subscribe(photoshoots => {
        console.log(photoshoots);
        this.photoshoots = photoshoots;
        console.log(this.photoshoots);
        /*for (let p of this.photoshoots) {
          p.date = p.date.toDateString();
        }*/

      });
  }

}
