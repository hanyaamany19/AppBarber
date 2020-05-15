<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Booking extends Model
{
    protected $table = 'booking';
    protected $fillable = ['nama','tgl','jam','layanan'];

    public $timestamps = false;
}
