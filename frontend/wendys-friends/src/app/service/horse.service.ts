import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Globals} from '../global/globals';
import {Observable} from 'rxjs';
import {Horse} from '../dto/horse';


@Injectable({
  providedIn: 'root'
})

export class HorseService {

  private messageBaseUri: string = this.globals.backendUri + '/horses';

  constructor(private httpClient: HttpClient, private globals: Globals) {
  }
  /**
   * @brief sends a post request to server
   * @param h horse object which gets sent
   */
  addNewHorse(h: Horse): Observable<Horse>{
  console.log('Add new horse: ' + JSON.stringify(h));
  return this.httpClient.post<Horse>(this.messageBaseUri,h,{responseType: 'json' as 'json'});
  }

  /**
   * @brief sends a post request to server
   * @param h horse to be updated
   */
  updateHorse(h: Horse): Observable<Horse>{
    console.log('Update horse: ' + h);
    return this.httpClient.put<Horse>(this.messageBaseUri,h,{responseType: 'json' as 'json'});
  }

  /**
   * @brief sends a delete request to the server
   * @param id of horse to be deleted
   */
  deleteHorse(id: number): Observable<Horse>{
    console.log('Delete horse with id: ' + id);
    return this.httpClient.delete<Horse>(this.messageBaseUri+'/'+id);
  }

  /**
   * @brief sends get request to the server for search
   * @param horse to be searched for
   */
  searchHorse(horse: Horse): Observable<Horse[]>{
    let tosend = '/search?';
    let andQualifier =false;
    if(horse.name!==null){
      tosend=tosend+'name='+horse.name;
      andQualifier=true;
    }
    if(horse.description!==null){
      if(andQualifier) tosend=tosend+'&';
      tosend=tosend+'description='+horse.description;
      andQualifier=true;
    }
    if(horse.race!==null){
      if(andQualifier) tosend=tosend+'&';
      tosend=tosend+'race='+horse.race;
      andQualifier=true;
    }
    if(horse.rating!==null){
      if(andQualifier) tosend=tosend+'&';
      tosend=tosend+'rating='+horse.rating;
      andQualifier=true;
    }
    if(horse.birthdate!==null){
      if(andQualifier) tosend=tosend+'&';
      tosend=tosend+'birthdate='+horse.birthdate;
    }

    console.log(this.messageBaseUri + tosend);
    return this.httpClient.get<Horse[]>(this.messageBaseUri + tosend);
  }
}
