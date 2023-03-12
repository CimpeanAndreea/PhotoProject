import {Address} from "./address.model";

export class Location{
  id: number;
  name: string;
  entranceFee: number;
  owner: string;
  timeLimit: number;
  address: Address;
}
