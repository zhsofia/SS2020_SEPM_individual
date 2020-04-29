export class Horse {
  constructor(
    public id:number,
    public name:string,
    public description:string,
    public rating:number,
    public birthdate:string,
    public createdAt:string,
    public updatedAt:string,
    public race: string,
    public idofowner:number,
    public imageBase64:string) {
  }

}
