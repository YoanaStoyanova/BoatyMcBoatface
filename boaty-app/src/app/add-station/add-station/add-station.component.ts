import { Router } from '@angular/router';
import { StationModel } from './../../model/station-model';
import { StationService } from './../../services/station.service';
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-add-station',
  templateUrl: './add-station.component.html',
  styleUrls: ['./add-station.component.css']
})
export class AddStationComponent implements OnInit {

  @Input()
  station: StationModel = {} as StationModel;

  constructor(private stationService: StationService,
    private router: Router) { }

  ngOnInit() {
  }

  submit(){
    this.stationService.addStation(this.station).subscribe(result => {
      console.log("Added station");
      this.router.navigateByUrl('/admin/stations');
    });
  }

}
