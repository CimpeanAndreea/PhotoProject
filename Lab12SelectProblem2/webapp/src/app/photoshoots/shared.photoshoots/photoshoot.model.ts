import {Location} from "../../locations/shared.locations/location.model";

export class Photoshoot{
  id: number;
  price: number;
  date: string;
  noMaxClients: number;
  location: Location;
  clients: Array<number>;
}
