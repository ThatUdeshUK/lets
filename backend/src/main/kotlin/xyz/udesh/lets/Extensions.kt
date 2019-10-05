package xyz.udesh.lets

import xyz.udesh.lets.dto.ResponseDto

fun String.toResponse() = ResponseDto(this)