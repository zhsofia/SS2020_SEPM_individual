import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Globals} from '../global/globals';
import {Observable} from 'rxjs';
import {Owner} from '../dto/owner';


@Injectable({
  providedIn: 'root'
})
export class OwnerService {

  private messageBaseUri: string = this.globals.backendUri + '/owners';

  constructor(private httpClient: HttpClient, private globals: Globals) {
  }

  /**
   * Loads specific owner from the backend
   * @param id of owner to load
   */
  getOwnerById(id: number): Observable<Owner> {
    console.log('Load owner details for ' + id);
    return this.httpClient.get<Owner>(this.messageBaseUri + '/' + id);
  }

  /**
   * @brief sends a post request to server to add owner
   * @param o owner object to be sent
   */
  addNewOwner(o: Owner): Observable<Owner>{
        return this.httpClient.post<Owner>(this.messageBaseUri,o);
  }

  /**
   * @brief sends a put request to server to update an owner
   * @param o owner object to be updated
   */
  updateOwner(o: Owner): Observable<Owner>{
    return this.httpClient.put<Owner>(this.messageBaseUri,o,);
  }
  /**
   * @brief sends a delete request to server to delete an owner
   * @param id of owner to be deleted
   */
  deleteOwner(id: number): Observable<Owner>{
    console.log(this.messageBaseUri + '/'+ id)
    return this.httpClient.delete<Owner>(this.messageBaseUri + '/'+ id);
  }

  /**
   * @brief sends get request to the server for search
   * @param name to be searched for
   */
  searchOwner(name: string): Observable<Owner[]>{
    console.log(this.messageBaseUri + '/search?name='+ name);
    return this.httpClient.get<Owner[]>(this.messageBaseUri + '/search?name='+ name);
  }
}
