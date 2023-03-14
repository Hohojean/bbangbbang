package teamPro.bbangShuttle.controller;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import teamPro.bbangShuttle.dto.ItemDTO;
import teamPro.bbangShuttle.security.TokenProvider;
import teamPro.bbangShuttle.service.ItemService;
import teamPro.bbangShuttle.service.ReviewService;
import teamPro.bbangShuttle.vo.ItemVO;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {
    private final ItemService itemService;
    private final ReviewService reviewService;
    private final TokenProvider tokenProvider;

    @GetMapping
    public ResponseEntity<?> itemList() {
        try {
            ItemDTO<ItemVO> response = ItemDTO.<ItemVO>builder()
                    .itemList(itemService.findAllItem())
                    .build();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            ItemDTO<ItemVO> response = ItemDTO.<ItemVO>builder()
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/{itemNo}")
    public ResponseEntity<?> itemDetail(@PathVariable int itemNo) {
        try {
            ItemDTO<ItemVO> response = ItemDTO.<ItemVO>builder()
                    .item(itemService.ItemDetail(itemNo))
                    .review(reviewService.itemReviewList(itemNo))
                    .build();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            ItemDTO<ItemVO> response = ItemDTO.<ItemVO>builder()
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping
    public ResponseEntity<?> itemSave(@RequestBody ItemVO vo, HttpServletRequest request) {
        try {
//            if (!"admin".equals(tokenProvider.validateAndGetUserId(getTokenFromRequest(request)))) {
//                throw new IllegalArgumentException("Unauthorized access");
//            }
            if (itemService.save(vo) < 1) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            ItemDTO<ItemVO> response = ItemDTO.<ItemVO>builder()
                    .itemList(itemService.findAllItem())
                    .build();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            ItemDTO<ItemVO> response = ItemDTO.<ItemVO>builder()
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> itemDelete(@RequestBody ItemVO vo,HttpServletRequest request) {
        try {
//            if (!"admin".equals(tokenProvider.validateAndGetUserId(getTokenFromRequest(request)))) {
//                throw new IllegalArgumentException("Unauthorized access");
//            }
            if (itemService.delete(vo) < 1) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            ItemDTO<ItemVO> response = ItemDTO.<ItemVO>builder()
                    .itemList(itemService.findAllItem())
                    .build();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            ItemDTO<ItemVO> response = ItemDTO.<ItemVO>builder()
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PatchMapping
    public ResponseEntity<?> itemUpdate(@RequestBody ItemVO vo, HttpServletRequest request) {
        try {
//            if (!"admin".equals(tokenProvider.validateAndGetUserId(getTokenFromRequest(request)))) {
//                throw new IllegalArgumentException("Unauthorized access");
//            }
            if (itemService.update(vo) < 1) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            ItemDTO<ItemVO> response = ItemDTO.<ItemVO>builder()
                    .itemList(itemService.findAllItem())
                    .build();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            ItemDTO<ItemVO> response = ItemDTO.<ItemVO>builder()
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (!StringUtils.isEmpty(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}