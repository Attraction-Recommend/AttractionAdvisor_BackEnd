package com.example.attractionadvisor_backend.init.csv.controller;

//@RestController
//@Slf4j
//@RequiredArgsConstructor
//public class UpdateAiModelController {
//
//    private final UpdateAttractionAiService updateAttractionAiService;
//
//    @PostMapping("/updateModel")
//    public Mono<ResponseEntity<String>> updateModel() {
//        return updateAttractionAiService.updateModel()
//                .map(ResponseEntity::ok)
//                .onErrorResume(this::handleError);
//    }
//
//    private Mono<ResponseEntity<String>> handleError(Throwable error) {
//        log.error("Error updating model", error);
//        if (error instanceof RecommendationException) {
//            return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Error updating model: " + error.getMessage()));
//        }
//        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body("An unexpected error occurred while updating the model"));
//    }
//}
