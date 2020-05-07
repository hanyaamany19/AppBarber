<?php

/*
 * Created by Oclemy for http://camposha.info and ProgrammingWizards TV.
 * User: Oclemy
 */
class Constants
{
    //DATABASE DETAILS
    static $DB_SERVER="localhost";
    static $DB_NAME="barber";
    static $USERNAME="root";
    static $PASSWORD="";

    //STATEMENTS
    static $SQL_SELECT_ALL="SELECT * FROM model";
}

class Model
{
    /*/
    /*
       1.CONNECT TO DATABASE.
       2. RETURN CONNECTION OBJECT
    */
    public function connect()
    {
        $con=new mysqli(Constants::$DB_SERVER,Constants::$USERNAME,Constants::$PASSWORD,Constants::$DB_NAME);
        if($con->connect_error)
        {
           // echo "Unable To Connect";
            return null;
        }else
        {
            return $con;
        }
    }
    public function insert()
    {
        // INSERT
        $con=$this->connect();
        if($con != null)
        {
            // Get image name
            $image_name = $_FILES['image']['name'];
            // Get text
            $name = mysqli_real_escape_string($con, $_POST['name']);

            // image file directory
            $target = "images/".basename($image_name);
            $sql = "INSERT INTO model (image, name) VALUES ('$image_name', '$name')";
            try
            {
                $result=$con->query($sql);
                if($result)
                {
                    if (move_uploaded_file($_FILES['image']['tmp_name'], $target)) {
                       print(json_encode(array("message"=>"Success")));
                    }else{
                       print(json_encode(array("message"=>"Saved But Unable to Move Image to Appropriate Folder")));
                    }
                }else
                {
                    print(json_encode(array("message"=>"Unsuccessful. Connection was successful but data could not be Inserted.")));
                }
                $con->close();
            }catch (Exception $e)
            {
                print(json_encode(array("message"=>"ERROR PHP EXCEPTION : CAN'T SAVE TO MYSQL. ".$e->getMessage())));
                $con->close();
            }
        }else{
            print(json_encode(array("message"=>"ERROR PHP EXCEPTION : CAN'T CONNECT TO MYSQL. NULL CONNECTION.")));
        }
    }
    /*/
    /*
       1.SELECT FROM DATABASE.
    */
    public function select()
    {
        $con=$this->connect();
        if($con != null)
        {
            $result=$con->query(Constants::$SQL_SELECT_ALL);
            if($result->num_rows > 0)
            {
                $model=array();
                while($row=$result->fetch_array())
                {
                    array_push($model, array("id"=>$row['id'],"name"=>$row['name'],"image"=>$row['image']));
                }
                print(json_encode(array_reverse($model)));
            }else
            {
            }
            $con->close();

        }else{
            print(json_encode(array("PHP EXCEPTION : CAN'T CONNECT TO MYSQL. NULL CONNECTION.")));
        }
    }
    public function handleRequest() {
        if (isset($_POST['name'])) {
            $this->insert();
        } else{
            $this->select();
        }
    }
}
$model=new Model();
$model->handleRequest();
