<?php

use Illuminate\Database\Seeder;
use App\Admin;

class AdminSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        //
        Admin::create([
        	"name" => "Admin Mahasiswa",
        	"username" => "Admin@gmail.com",
        	"password" => \Hash::make("admin123"),
        ]);
    }
}
