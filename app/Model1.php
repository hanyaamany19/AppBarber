<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Model1 extends Model
{
    protected $table = 'models';
    protected $fillable = ['nama'];
}