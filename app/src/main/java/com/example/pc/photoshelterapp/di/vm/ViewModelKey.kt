package com.example.pc.photoshelterapp.di.vm

import androidx.lifecycle.ViewModel
import dagger.MapKey
import java.lang.annotation.RetentionPolicy
import kotlin.reflect.KClass


@MustBeDocumented
@kotlin.annotation.Retention
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)