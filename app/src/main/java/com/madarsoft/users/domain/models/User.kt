package com.madarsoft.users.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User( @PrimaryKey(autoGenerate = true)
                 val  id : Int?    = null,
                 val name : String? =null ,
                 val age :String? =null ,
                 val jobTitle:String? =null ,
                 val gender : String? =null )
