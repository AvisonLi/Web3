import random
import math
from typing import Tuple

class RSA:
    def __init__(self, key_size: int = 1024):
        """
        Initialize RSA with a specified key size (in bits)
        """
        self.key_size = key_size
        self.public_key = None
        self.private_key = None
        
    def is_prime(self, n: int, k: int = 5) -> bool:
        """
        Miller-Rabin primality test
        """
        if n < 2:
            return False
        for p in [2, 3, 5, 7, 11, 13, 17, 19, 23, 29]:
            if n % p == 0:
                return n == p
        # Write n-1 as d * 2^s
        s, d = 0, n - 1
        while d % 2 == 0:
            s += 1
            d //= 2
        # Test k times
        for _ in range(k):
            a = random.randrange(2, n - 1)
            x = pow(a, d, n)
            if x == 1 or x == n - 1:
                continue
            for _ in range(s - 1):
                x = (x * x) % n
                if x == n - 1:
                    break
            else:
                return False
        return True
    
    def generate_prime(self) -> int:
        """
        Generate a large prime number
        """
        while True:
            # Generate a random odd number
            p = random.getrandbits(self.key_size // 2)
            p |= (1 << (self.key_size // 2 - 1)) | 1
            if self.is_prime(p):
                return p
    
    def extended_gcd(self, a: int, b: int) -> Tuple[int, int, int]:
        """
        Extended Euclidean Algorithm
        Returns (gcd, x, y) such that ax + by = gcd(a, b)
        """
        if a == 0:
            return b, 0, 1
        gcd, x1, y1 = self.extended_gcd(b % a, a)
        x = y1 - (b // a) * x1
        y = x1
        return gcd, x, y
    
    def mod_inverse(self, a: int, m: int) -> int:
        """
        Find modular inverse of a mod m
        """
        gcd, x, _ = self.extended_gcd(a, m)
        if gcd != 1:
            raise ValueError("Modular inverse doesn't exist")
        return x % m
    
    def generate_keys(self) -> Tuple[Tuple[int, int], Tuple[int, int]]:
        """
        Generate RSA public and private keys
        Returns: (public_key, private_key)
        """
        # Step 1: Generate two large primes
        p = self.generate_prime()
        q = self.generate_prime()
        
        # Ensure p and q are different
        while q == p:
            q = self.generate_prime()
        
        # Step 2: Compute n = p * q
        n = p * q
        
        # Step 3: Compute Euler's totient function
        phi = (p - 1) * (q - 1)
        
        # Step 4: Choose public exponent e
        e = 65537  # Common choice
        while math.gcd(e, phi) != 1:
            e = random.randrange(2, phi)
        
        # Step 5: Compute private exponent d
        d = self.mod_inverse(e, phi)
        
        # Store keys
        self.public_key = (e, n)
        self.private_key = (d, n)
        
        return self.public_key, self.private_key
    
    def encrypt(self, message: str, public_key: Tuple[int, int] = None) -> str:
        """
        Encrypt a message using RSA
        """
        if public_key is None:
            if self.public_key is None:
                raise ValueError("No public key available")
            e, n = self.public_key
        else:
            e, n = public_key
        
        # Convert message to bytes then to integer
        message_bytes = message.encode('utf-8')
        m = int.from_bytes(message_bytes, 'big')
        
        # Ensure message is smaller than n
        if m >= n:
            raise ValueError("Message too large for key size")
        
        # Encrypt: c = m^e mod n
        c = pow(m, e, n)
        
        # Convert to hex string for easy representation
        return hex(c)[2:]
    
    def decrypt(self, ciphertext: str, private_key: Tuple[int, int] = None) -> str:
        """
        Decrypt a message using RSA
        """
        if private_key is None:
            if self.private_key is None:
                raise ValueError("No private key available")
            d, n = self.private_key
        else:
            d, n = private_key
        
        # Convert from hex string to integer
        c = int(ciphertext, 16)
        
        # Decrypt: m = c^d mod n
        m = pow(c, d, n)
        
        # Convert integer back to string
        # Calculate number of bytes needed
        byte_length = (m.bit_length() + 7) // 8
        message_bytes = m.to_bytes(byte_length, 'big')
        
        return message_bytes.decode('utf-8')
    
    def encrypt_symmetric(self, message: str, public_key: Tuple[int, int] = None) -> Tuple[str, str, str]:
        """
        Simulate hybrid encryption: 
        1. Generate a random symmetric key
        2. Encrypt message with symmetric key (simulated)
        3. Encrypt symmetric key with RSA
        """
        # Generate random symmetric key (simplified - in reality use proper crypto)
        sym_key = random.getrandbits(128)
        sym_key_str = hex(sym_key)[2:]
        
        # Simulate symmetric encryption (XOR for demonstration - NOT secure for real use)
        encrypted_message = ''
        key_byte = sym_key & 0xFF
        for char in message:
            encrypted_char = chr(ord(char) ^ key_byte)
            encrypted_message += encrypted_char
        
        # Convert to hex for storage
        encrypted_message_hex = encrypted_message.encode('utf-8').hex()
        
        # Encrypt symmetric key with RSA
        encrypted_sym_key = self.encrypt(sym_key_str, public_key)
        
        return encrypted_message_hex, encrypted_sym_key, sym_key_str
    
    def decrypt_symmetric(self, encrypted_message_hex: str, 
                         encrypted_sym_key: str,
                         private_key: Tuple[int, int] = None) -> str:
        """
        Decrypt hybrid encrypted message
        """
        # Decrypt symmetric key with RSA
        sym_key_str = self.decrypt(encrypted_sym_key, private_key)
        sym_key = int(sym_key_str, 16)
        
        # Convert hex back to string
        encrypted_message = bytes.fromhex(encrypted_message_hex).decode('utf-8')
        
        # Simulate symmetric decryption
        decrypted_message = ''
        key_byte = sym_key & 0xFF
        for char in encrypted_message:
            decrypted_char = chr(ord(char) ^ key_byte)
            decrypted_message += decrypted_char
        
        return decrypted_message


def main():
    """Main demonstration function"""
    print("=" * 60)
    print("RSA PUBLIC AND PRIVATE KEY ENCRYPTION/DECRYPTION")
    print("=" * 60)
    
    # Create RSA instance
    rsa = RSA(key_size=512)  # Using 512 bits for demonstration (1024+ recommended for real use)
    
    # Generate keys
    print("\n1. GENERATING RSA KEYS...")
    public_key, private_key = rsa.generate_keys()
    print(f"   Public Key (e, n): ({public_key[0]}, ...)")
    print(f"   Private Key (d, n): ({private_key[0]}, ...)")
    print(f"   n (modulus) has {public_key[1].bit_length()} bits")
    
    # Demonstrate basic RSA encryption/decryption
    print("\n2. BASIC RSA ENCRYPTION/DECRYPTION")
    message = "Hello, World!"
    print(f"   Original message: {message}")
    
    # Encrypt
    ciphertext = rsa.encrypt(message, public_key)
    print(f"   Encrypted (hex): {ciphertext[:50]}...")
    
    # Decrypt
    decrypted = rsa.decrypt(ciphertext, private_key)
    print(f"   Decrypted message: {decrypted}")
    
    # Demonstrate hybrid encryption
    print("\n3. HYBRID ENCRYPTION (RSA + Symmetric)")
    long_message = "This is a longer secret message that needs to be securely transmitted!"
    print(f"   Original message: {long_message}")
    
    # Encrypt with hybrid approach
    encrypted_msg, encrypted_key, original_key = rsa.encrypt_symmetric(long_message, public_key)
    print(f"   Encrypted symmetric key (RSA): {encrypted_key[:50]}...")
    print(f"   Encrypted message (symmetric): {encrypted_msg[:50]}...")
    print(f"   Original symmetric key: {original_key}")
    
    # Decrypt hybrid
    decrypted_msg = rsa.decrypt_symmetric(encrypted_msg, encrypted_key, private_key)
    print(f"   Decrypted message: {decrypted_msg}")
    
    # Demonstrate using keys from external sources
    print("\n4. USING EXTERNALLY PROVIDED KEYS")
    # Simulate Bob sending a message to Alice
    print("\n   Bob wants to send a message to Alice:")
    
    # Bob gets Alice's public key
    alice_rsa = RSA(key_size=512)
    alice_public, alice_private = alice_rsa.generate_keys()
    
    # Bob's message
    bob_message = "Meet me at 5 PM tomorrow."
    print(f"   Bob's message: {bob_message}")
    
    # Bob encrypts with Alice's public key
    bob_cipher = alice_rsa.encrypt(bob_message, alice_public)
    print(f"   Encrypted message (sent to Alice): {bob_cipher[:50]}...")
    
    # Alice decrypts with her private key
    alice_decrypted = alice_rsa.decrypt(bob_cipher, alice_private)
    print(f"   Alice decrypts: {alice_decrypted}")
    
    # Security considerations
    print("\n5. SECURITY CONSIDERATIONS")
    print("   - RSA with 512-bit keys is for DEMONSTRATION ONLY")
    print("   - Real applications require 2048-bit or larger keys")
    print("   - Always use proper padding (OAEP) in real implementations")
    print("   - Never implement crypto for production without security review")
    print("   - This demo uses XOR for symmetric encryption - NOT SECURE!")
    
    print("\n" + "=" * 60)
    print("DEMONSTRATION COMPLETE")
    print("=" * 60)


def test_encryption():
    """Simple test function"""
    print("\nQUICK TEST:")
    rsa = RSA(key_size=256)  # Small for quick testing
    
    # Generate keys
    public, private = rsa.generate_keys()
    
    # Test message
    test_msg = "Test123"
    
    # Encrypt and decrypt
    encrypted = rsa.encrypt(test_msg, public)
    decrypted = rsa.decrypt(encrypted, private)
    
    print(f"Original: {test_msg}")
    print(f"Encrypted: {encrypted}")
    print(f"Decrypted: {decrypted}")
    print(f"Success: {test_msg == decrypted}")


if __name__ == "__main__":
    try:
        main()
        # Uncomment to run quick test
        # test_encryption()
    except ValueError as e:
        print(f"Error: {e}")
    except Exception as e:
        print(f"Unexpected error: {e}")