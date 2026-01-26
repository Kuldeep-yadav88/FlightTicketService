import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable, map, startWith } from 'rxjs';
import { Options } from '../../shared/shared';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent  implements OnInit{
  minDate = new Date();
  isHovered: boolean[] = [false, false, false, false];
  selected='1';
  classEco= 'option1';
  myControl = new FormControl('');
  myControlTo = new FormControl('');
  dateControl = new FormControl('');
  options:any = Options
  filteredOptions!: Observable<any[]>
  filteredOptionsTo!: Observable<any[]>
  

  constructor(private router: Router){}

  ngOnInit(){
    this.filteredOptions = this.myControl.valueChanges.pipe(
      startWith(''),
      map(value => this._filter(value || '')),
    );
    
    this.filteredOptionsTo = this.myControlTo.valueChanges.pipe(
      startWith(''),
      map(value => this._filter(value || '')),
    );
  }

  private _filter(value: string): any[] {
    const filterValue = value.toLowerCase();
    return this.options.filter((option: { view_value: string; }) => option.view_value.toLowerCase().includes(filterValue));
  }

  searchFlights() {
    const fromValue = this.myControl.value || 'Delhi';
    const toValue = this.myControlTo.value || 'Mumbai';
    const dateValue = this.dateControl.value || new Date().toISOString();
    const travelClass = this.classEco === 'option1' ? 'Economy' : this.classEco === 'option2' ? 'Business' : 'Premium Economy';
    
    this.router.navigate(['/flight-results'], {
      queryParams: {
        from: fromValue,
        to: toValue,
        departOn: dateValue,
        travelClass: travelClass,
        passengers: this.selected || '1'
      }
    });
  }
  
  onMouseEnter(index: number) {
    this.isHovered[index] = true;
  }

  onMouseLeave(index: number){
    this.isHovered[index] = false;
  }



}
