import { Component, OnInit } from '@angular/core';
import {PhotoshootService} from "../../photoshoots/shared.photoshoots/photoshoot.service";
import {ClientService} from "../shared.clients/client.service";
import {Photoshoot} from "../../photoshoots/shared.photoshoots/photoshoot.model";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-enroll-photoshoot',
  templateUrl: './enroll-photoshoot.component.html',
  styleUrls: ['./enroll-photoshoot.component.css']
})
export class EnrollPhotoshootComponent implements OnInit {

  clientId: number;
  photoshoots: Photoshoot[];

  constructor(private route: ActivatedRoute, private photoshootService: PhotoshootService, private clientService: ClientService) { }

  ngOnInit(): void {
    this.route.params.subscribe(
      params => {
        this.clientId = +params.id;
        this.getPhotoshoots();
      }
    );
  }

  getPhotoshoots(): void{
    this.photoshootService.getPhotoshoots().subscribe(
      photoshoots => {
        const allPhotoshoots = photoshoots;
        this.photoshoots = allPhotoshoots.filter(p => !p.clients.includes(this.clientId))
        console.log(photoshoots);
      }
    )
  }

  enroll(photoshoot: Photoshoot): void{
    this.clientService.enroll(this.clientId, photoshoot)
      .subscribe(response => {
          window.location.reload();
        }
      );
  }

}
