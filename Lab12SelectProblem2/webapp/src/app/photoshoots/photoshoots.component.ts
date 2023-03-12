import { Component, OnInit } from '@angular/core';
import {Photoshoot} from "./shared.photoshoots/photoshoot.model";
import {PictureService} from "../pictures/shared/picture.service";
import {PhotoshootService} from "./shared.photoshoots/photoshoot.service";

@Component({
  selector: 'app-photoshoots',
  templateUrl: './photoshoots.component.html',
  styleUrls: ['./photoshoots.component.css']
})
export class PhotoshootsComponent implements OnInit {

  photoshoots: Photoshoot[];
  noClients: number[];

  constructor(private photoshootService: PhotoshootService) { }

  ngOnInit(): void {
    this.noClients = new Array<number>();
    this.getPhotoshoots();

  }

  getPhotoshoots(): void{
    this.photoshootService.getPhotoshoots()
      .subscribe(photoshoots => {
        this.photoshoots = photoshoots;
        console.log(this.photoshoots);
      });
  }

}
