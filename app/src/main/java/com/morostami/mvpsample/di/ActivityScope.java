package com.morostami.mvpsample.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

import kotlin.annotation.AnnotationRetention;

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScope {}
